package com.cookos;

import java.net.*;
import java.io.*;

public class App 
{
    public static void main( String[] args )
    {
        try (var socket = new DatagramSocket(8080)) {

            var bufx = new byte[64];
            var bufy = new byte[64];
            var bufz = new byte[64];
            var xPacket = new DatagramPacket(bufx, bufx.length);
            var yPacket = new DatagramPacket(bufy, bufy.length);
            var zPacket = new DatagramPacket(bufz, bufz.length);

            socket.receive(xPacket);
            socket.receive(yPacket);
            socket.receive(zPacket);

            var address = xPacket.getAddress();
            int port = xPacket.getPort();

            int xBits = (int)Long.parseLong(new String(bufx, 0, xPacket.getLength()), 2);
            int yBits = (int)Long.parseLong(new String(bufy, 0, yPacket.getLength()), 2);
            int zBits = (int)Long.parseLong(new String(bufz, 0, zPacket.getLength()), 2);
            float x = Float.intBitsToFloat(xBits);
            float y = Float.intBitsToFloat(yBits);
            float z = Float.intBitsToFloat(zBits);

            float result = f(x, y, z);

            System.out.println(result);

            var sendBuf = Integer.toBinaryString(Float.floatToIntBits(result)).getBytes();
            var resultPacket = new DatagramPacket(sendBuf, sendBuf.length, address, port);

            socket.send(resultPacket);

            var file = new FileWriter("text.txt", true);
            file.write(address.toString() + ":" + port + "\n");
            file.write("x = " + x + "\n");
            file.write("y = " + y + "\n");
            file.write("z = " + z + "\n");
            file.write("result = " + result + "\n\n");
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static float f(float x, float y, float z) {
        
        return (float) (
        Math.log10(
            Math.sqrt(
                Math.exp(x - y) + 
                Math.pow(x, Math.abs(y)) +
                z
            )
        ) * (
            x -
            x*x*x / 3 -
            Math.pow(x, 7) / 7
        ));

    }
}
