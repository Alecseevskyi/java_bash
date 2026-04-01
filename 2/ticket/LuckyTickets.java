package ticket;

public class LuckyTickets {
    
    public static boolean isLuckyTicket(int ticketNumber) {
        String formattedNumber = String.format("%06d", ticketNumber);
        
        int sumFirstHalf = 0;
        for (int i = 0; i < 3; i++) {
            sumFirstHalf += Character.getNumericValue(formattedNumber.charAt(i));
        }
        
        int sumSecondHalf = 0;
        for (int i = 3; i < 6; i++) {
            sumSecondHalf += Character.getNumericValue(formattedNumber.charAt(i));
        }
        
        return sumFirstHalf == sumSecondHalf;
    }
    
    public static int countAllLuckyTickets() {
        int count = 0;
        
        for (int i = 0; i < 1000000; i++) {
            if (isLuckyTicket(i)) {
                count++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int count = countAllLuckyTickets();
        System.out.println("Количество счастливых билетов: " + count);
    }
}

