package pseudorandom;

import java.io.*;

public class HC1 {
	public static void main(String[] args) {
		try {
			if(args.length != 3){
				System.out.println("-- usage: java -cp hc1.jar pseudorandom.HC1 <seed> <file2crypt/decrypt> <target>");
				System.exit(1);
			}
			System.out.println("Inital seed value for pseudo random numbers: " + args[0]);
			LCG lcg = new LCG(Long.valueOf(args[0]).longValue());

			System.out.println("Get file2crypt: " + args[1]);
			FileInputStream fis = new FileInputStream(args[1]);

			System.out.println("Create file4crypt msg: " + args[2]);
			FileOutputStream fos = new FileOutputStream(args[2]);

			System.out.println("Starting stream cipher...");

			long b;
			while (fis.available() > 0) {
				b = fis.read();
				b = b ^ Double.doubleToRawLongBits(lcg.nextValue());
				fos.write((int) b);
			}
			fis.close();
			fos.close();

			System.out.println("... stream cipher ending!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
