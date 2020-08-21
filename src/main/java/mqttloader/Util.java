package mqttloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

public class Util {
    public static void output(String filename, String str, boolean append){
        File file = new File(filename);

        if(!file.exists() || file == null){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);

            bw.write(str);

            bw.close();
            osw.close();
            fos.close();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
                if(osw != null) osw.close();
                if(fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public static byte[] genPayloads(int size) {
        return ByteBuffer.allocate(size).putLong(getTime()).array();
    }

    public static long getTime() {
        return System.currentTimeMillis() + Loader.offset;
    }
}