
public class UavdataPointSystemStatus extends UavDataPoint {
	 int onboard_control_sensors_present; /*< Bitmask showing which onboard controllers and sensors are present. Value of 0: not present. Value of 1: present. Indices defined by ENUM MAV_SYS_STATUS_SENSOR*/
	 int onboard_control_sensors_enabled; /*< Bitmask showing which onboard controllers and sensors are enabled:  Value of 0: not enabled. Value of 1: enabled. Indices defined by ENUM MAV_SYS_STATUS_SENSOR*/
	 int onboard_control_sensors_health; /*< Bitmask showing which onboard controllers and sensors are operational or have an error:  Value of 0: not enabled. Value of 1: enabled. Indices defined by ENUM MAV_SYS_STATUS_SENSOR*/
	 int load; /*< Maximum usage in percent of the mainloop time, (0%: 0, 100%: 1000) should be always below 1000*/
	 int voltage_battery; /*< Battery voltage, in millivolts (1 = 1 millivolt)*/
	 int current_battery; /*< Battery current, in 10*milliamperes (1 = 10 milliampere), -1: autopilot does not measure the current*/
	 int drop_rate_comm; /*< Communication drops in percent, (0%: 0, 100%: 10'000), (UART, I2C, SPI, CAN), dropped packets on all links (packets that were corrupted on reception on the MAV)*/
	 int errors_comm; /*< Communication errors (UART, I2C, SPI, CAN), dropped packets on all links (packets that were corrupted on reception on the MAV)*/
	 int errors_count1; /*< Autopilot-specific errors*/
	 int errors_count2; /*< Autopilot-specific errors*/
	 int errors_count3; /*< Autopilot-specific errors*/
	 int errors_count4; /*< Autopilot-specific errors*/
	 int battery_remaining; /*< Remaining battery energy: (0%: 0, 100%: 100), -1: autopilot estimate the remaining battery*/
	
	
	
	public UavdataPointSystemStatus(String str,String split) {
		String[] strArray = str.split(split);
		DateInlong(strArray[0]);
		onboard_control_sensors_present = Integer.parseInt(strArray[11]);
		onboard_control_sensors_enabled= Integer.parseInt(strArray[13]);
		onboard_control_sensors_health= Integer.parseInt(strArray[15]);
		load= Integer.parseInt(strArray[17]);
		voltage_battery= Integer.parseInt(strArray[19]);
		current_battery= Integer.parseInt(strArray[21]);
		drop_rate_comm= Integer.parseInt(strArray[23]);
		errors_comm= Integer.parseInt(strArray[25]);
		errors_count1 = Integer.parseInt(strArray[27]);
		errors_count2 = Integer.parseInt(strArray[29]);
		errors_count3 = Integer.parseInt(strArray[31]);
		errors_count4 = Integer.parseInt(strArray[33]);
		battery_remaining = Integer.parseInt(strArray[35]);
		if (battery_remaining == 255) {
			battery_remaining = -1;
			
		}
	}
	public UavdataPointSystemStatus(String str) {
		String[] strArray = str.split(",");
		
		setTime(Long.parseLong((strArray[0])));
		onboard_control_sensors_present = Integer.parseInt(strArray[1]);
		onboard_control_sensors_enabled= Integer.parseInt(strArray[2]);
		onboard_control_sensors_health= Integer.parseInt(strArray[3]);
		load= Integer.parseInt(strArray[4]);
		voltage_battery= Integer.parseInt(strArray[5]);
		current_battery= Integer.parseInt(strArray[6]); 
		drop_rate_comm= Integer.parseInt(strArray[7]);
		errors_comm= Integer.parseInt(strArray[8]);
		errors_count1 = Integer.parseInt(strArray[9]);
		errors_count2 = Integer.parseInt(strArray[10]);
		errors_count3 = Integer.parseInt(strArray[11]);
		errors_count4 = Integer.parseInt(strArray[12]);
		battery_remaining = Integer.parseInt(strArray[13]);
		if (battery_remaining == 255) {
			battery_remaining = -1;
			
		}
	}
	@Override
	public String toString() {
		return getTime() + "," + onboard_control_sensors_present+","+ onboard_control_sensors_enabled+","+ onboard_control_sensors_health + ","+ load +","+ voltage_battery 
				+ ","+current_battery + ","+ drop_rate_comm+ ","+ errors_comm + ","+ errors_count1 +","+ errors_count2 +","+ errors_count3  +","+errors_count4 +","+ battery_remaining;
	}
	public int getOnboard_control_sensors_present() {
		return onboard_control_sensors_present;
	}
	public int getOnboard_control_sensors_enabled() {
		return onboard_control_sensors_enabled;
	}
	public int getOnboard_control_sensors_health() {
		return onboard_control_sensors_health;
	}
	public int getLoad() {
		return load;
	}
	public int getVoltage_battery() {
		return voltage_battery;
	}
	public int getCurrent_battery() {
		return current_battery;
	}
	public int getDrop_rate_comm() {
		return drop_rate_comm;
	}
	public int getErrors_comm() {
		return errors_comm;
	}
	public int getErrors_count1() {
		return errors_count1;
	}
	public int getErrors_count2() {
		return errors_count2;
	}
	public int getErrors_count3() {
		return errors_count3;
	}
	public int getErrors_count4() {
		return errors_count4;
	}
	public int getBattery_remaining() {
		return battery_remaining;
	}
	
}
