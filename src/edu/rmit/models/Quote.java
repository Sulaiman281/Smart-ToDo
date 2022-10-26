package edu.rmit.models;

import java.util.Random;

public class Quote {
    private static String[] quotes = {
            "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
            "Live the Life of Your Dreams: Be brave enough to live the life of your dreams according to your vision and purpose instead of the expectations and opinions of others.",
            "Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.",
            "It’s only after you’ve stepped outside your comfort zone that you begin to change, grow, and transform.",
            "More smiling, less worrying. More compassion, less judgment. More blessed, less stressed. More love, less hate.",
            "Success is not how high you have climbed, but how you make a positive difference to the world.",
            "Your hardest times often lead to the greatest moments of your life. Keep going. Tough situations build strong people in the end.",
            "Pursue what catches your heart, not what catches your eyes.",
            "Be grateful for what you already have while you pursue your goals. If you are not grateful for what you already have, what makes you think you would be happy with more.",
            "Start each day with a positive thought and a grateful heart.",
            "Life is about accepting the challenges along the way, choosing to keep moving forward, and savoring the journey.",
            "Be brave to stand for what you believe in even if you stand alone."
    };

    public static String getRandomQuote(){
        return quotes[new Random().nextInt(quotes.length)];
    }
}
