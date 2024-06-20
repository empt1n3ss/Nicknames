import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final AtomicInteger countLength3 = new AtomicInteger();
    private static final AtomicInteger countLength4 = new AtomicInteger();
    private static final AtomicInteger countLength5 = new AtomicInteger();

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = GenerateText.generateText("abc", 3 + random.nextInt(3));
        }
        Thread palindromeThread = new Thread(() -> {
            for (String text : texts) {
                if (Palindrome.isPalindrome(text)) {
                    updateCount(text);
                }
            }
        });
        Thread sameLettersThread = new Thread(() -> {
            for (String text : texts) {
                if (SameLetters.isSameLetters(text)) {
                    updateCount(text);
                }
            }
        });

        Thread increasingThread = new Thread(() -> {
            for (String text : texts) {
                if (Increasing.isIncreasing(text)) {
                    updateCount(text);
                }
            }
        });

        palindromeThread.start();
        sameLettersThread.start();
        increasingThread.start();

        try {
            palindromeThread.join();
            sameLettersThread.join();
            increasingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + countLength3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + countLength4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + countLength5.get() + " шт");
    }

    private static void updateCount(String text) {
        switch (text.length()) {
            case 3:
                countLength3.incrementAndGet();
                break;
            case 4:
                countLength4.incrementAndGet();
                break;
            case 5:
                countLength5.incrementAndGet();
                break;
        }
    }
}