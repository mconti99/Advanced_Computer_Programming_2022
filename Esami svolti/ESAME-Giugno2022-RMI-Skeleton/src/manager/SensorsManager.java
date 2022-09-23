package manager;

public class SensorsManager {
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			SensorT thread=new SensorT();
			thread.start();
			System.out.println("[MANAGER] Sensor thread startato");
		}
	}
}
