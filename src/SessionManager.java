import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.io.*;

public class SessionManager {

    ArrayList<Session> sessions = new ArrayList<>();
    Session currentSession = null;
    private final String fileName = "sessions.dat";

    public void saveSessions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(sessions);
        } catch (IOException e) {
            System.out.println("Error saving sessions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadSessions() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            sessions = (ArrayList<Session>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading sessions: " + e.getMessage());
        }
    }

    public void startSession(Scanner scanner) {
        if (currentSession != null) {
            System.out.println("There is an active session already!");
            return;
        };
        System.out.print("Enter subject: ");
        scanner.nextLine();
        String subject = scanner.nextLine();
        Session session = new Session();
        session.subject = subject;
        session.startTime = LocalDateTime.now();
        currentSession = session;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Session started at " + session.startTime.format(formatter));
    }

    public void stopSession() {
        if (currentSession == null) {
            System.out.println("There is no active session!");
            return;
        }
        currentSession.endTime = LocalDateTime.now();
        sessions.add(currentSession);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Session stopped at " + currentSession.endTime.format(formatter));
        currentSession = null;
        saveSessions();
    }

    public void showTodayStats() {
        LocalDate today = LocalDate.now();
        long totalMinutes = 0;
        for (Session s : sessions) {
            if (s.startTime.toLocalDate().equals(today)) {
                totalMinutes += Duration.between(s.startTime, s.endTime).toMinutes();
            }
        }
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        System.out.println("Today's total study time: " + hours + "h " + minutes + "m");
    }

    public void showWeeklyStats() {
        LocalDate today = LocalDate.now();
        long totalMinutes = 0;
        for (Session s : sessions) {
            long daysBetween = ChronoUnit.DAYS.between(s.startTime.toLocalDate(), today);
            if (daysBetween >= 0 && daysBetween < 7) {
                totalMinutes += Duration.between(s.startTime, s.endTime).toMinutes();
            }
        }
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        System.out.println("Study time for the last 7 days: " + hours + "h " + minutes + "m");
    }

    public void showSubStats() {
        HashMap<String, Long> subjectTime = new HashMap<>();
        for (Session s : sessions) {
            long durationMinutes = Duration.between(s.startTime, s.endTime).toMinutes();
            subjectTime.put(s.subject, subjectTime.getOrDefault(s.subject, 0L) + durationMinutes);
        }
        for (String subject : subjectTime.keySet()) {
            long totalMinutes = subjectTime.get(subject);
            long hours = totalMinutes / 60;
            long minutes = totalMinutes % 60;
            System.out.println(subject + " - " + hours + "h " + minutes + "m");
        }
    }
}