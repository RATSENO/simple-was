package nhn;

import nhn.util.SettingUtil;

public class Temp {

	public static void main(String[] args) {
		System.out.println(SettingUtil.getClientThreadPoolCount());
		System.out.println(SettingUtil.getServerPort());
		System.out.println(SettingUtil.getRootDirectoryName());
	}
}
