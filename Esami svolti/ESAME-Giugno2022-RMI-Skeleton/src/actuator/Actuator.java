package actuator;

public class Actuator {
	public static void main(String[] args) {
	ActuatorImpl actuator=new ActuatorImpl(Integer.parseInt(args[0]), args[1]);
	actuator.runSkeleton();
	System.out.println("[ACTUATOR] Actuator runnato");
	}
}
