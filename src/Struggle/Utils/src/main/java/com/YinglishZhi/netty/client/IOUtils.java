package com.YinglishZhi.netty.client;

import java.io.*;

/***
 * This is a utility class providing a reader/writer capability required by the
 * weatherTelnet, rexec, rshell, and rlogin example programs. The only point of
 * the class is to hold the static method readWrite which spawns a reader thread
 * and a writer thread. The reader thread reads from a local input source
 * (presumably stdin) and writes the data to a remote output destination. The
 * writer thread reads from a remote input source and writes to a local output
 * destination. The threads terminate when the remote input source closes.
 ***/

public final class IOUtil {

    public static final void readWrite(final InputStream remoteInput, final OutputStream remoteOutput,
                                       final InputStream localInput, final Writer localOutput) {
        Thread reader, writer;

        reader = new Thread(() -> {
            int ch;

            try {
                while (!Thread.interrupted() && (ch = localInput.read()) != -1) {
                    remoteOutput.write(ch);
                    remoteOutput.flush();
                }
            } catch (IOException e) {
                // e.printStackTrace();
            }
        });

        writer = new Thread(() -> {
            try {
                InputStreamReader reader1 = new InputStreamReader(remoteInput);
                while (true) {
                    int singleChar = reader1.read();
                    if (singleChar == -1) {
                        break;
                    }
                    System.out.println((char) singleChar);
//                    remoteOutput.write(singleChar);
//                    remoteOutput.flush();
//                        localOutput.write(singleChar);
//                        localOutput.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });

        writer.setPriority(Thread.currentThread().getPriority() + 1);

        writer.start();
        reader.setDaemon(true);
        reader.start();

        try {
            writer.join();
            reader.interrupt();
        } catch (InterruptedException e) {
            // Ignored
        }
    }

}