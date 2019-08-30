package me.mrCookieSlime.Slimefun.Setup;

import java.io.File;

public final class Files {
	
	private Files() {}
	
	public static File Researches = new File("plugins/Slimefun/Researches.yml");
	public static File config = new File("plugins/Slimefun/config.yml");
	public static File items = new File("plugins/Slimefun/Items.yml");
	public static File database = new File("data-storage/Slimefun/Players");
	public static File whitelist = new File("plugins/Slimefun/whitelist.yml");
	
	public static void cleanup() {
		if (!Researches.exists()) {
			System.err.println("###############################################");
			System.err.println("############## = -  INFO  - = #################");
			System.err.println("###############################################");
			System.err.println("      ");
			System.err.println("Slimefun Warning:");
			System.err.println("         ");
			System.err.println("Slimefun has detected that your Files are either");
			System.err.println("outdated or do not exist. We generated new Files");
			System.err.println("instead otherwise Slimefun would not work. If you");
			System.err.println("have used Slimefun before, your Settings are now");
			System.err.println("gone. But therefore Slimefun works!");
			delete(new File("plugins/Slimefun"));
			delete(new File("data-storage/Slimefun"));
		}
		
		if (!database.exists()) {
			database.mkdirs();
		}
	}
	
	public static boolean delete(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File current: files) {
				if (current.isDirectory()) {
					if (!delete(current)) return false;
				}
				else {
					if (!current.delete()) return false;
				}
			}
		}
		
		return folder.delete();
	}

}
