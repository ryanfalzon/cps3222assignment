package messagingapp;

import java.util.Random;

public class JohnDoe implements Supervisor {

    // Private final variables for session key generation
    private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lower = upper.toLowerCase();
    private final String numbers = "0123456789";
    private final String alphanumerical = upper + lower + numbers;

    // A method to create a login key for an agent
    public LoginKey getLoginKey(Agent agent) {

        // Check if id ofd the agent starts with a spy
        if(agent.getId().contains("spy")){
            return null;
        }
        else{
            return new LoginKey(getloginKey(10), System.currentTimeMillis(), agent.getId());
        }
    }

    // Method to generate a session key
    public String getloginKey(int counter) {

        Random random = new Random();
        String sessionKey = "";

        // Generate a session key according to the length passed to the method
        for (int i = 0; i < counter; i++) {
            sessionKey += alphanumerical.charAt(random.nextInt(alphanumerical.length()));
        }

        return sessionKey;
    }
}