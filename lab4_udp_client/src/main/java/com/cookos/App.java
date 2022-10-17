package com.cookos;

import java.net.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try (var socket = new DatagramSocket(); var in = new Scanner(System.in)) {

            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 8080;
            
            float x = GetFloat(in, "Введите х: ");

            var buf = Integer.toBinaryString(Float.floatToIntBits(x)).getBytes();
            var xPacket = new DatagramPacket(buf, buf.length, address, port);
            
            socket.send(xPacket);

            float y = GetFloat(in, "Введите y: ");

            buf = Integer.toBinaryString(Float.floatToIntBits(y)).getBytes();
            var yPacket = new DatagramPacket(buf, buf.length, address, port);

            socket.send(yPacket);

            float z = GetFloat(in, "Введите z: ");

            buf = Integer.toBinaryString(Float.floatToIntBits(z)).getBytes();
            var zPacket = new DatagramPacket(buf, buf.length, address, port);

            socket.send(zPacket);

            var resultBuf = new byte[64];
            var resultPacket = new DatagramPacket(resultBuf, resultBuf.length);

            socket.receive(resultPacket);

            int resultBits = (int)Long.parseLong(new String(resultBuf, 0, resultPacket.getLength()), 2);
            float result = Float.intBitsToFloat(resultBits);

            System.out.println("Ответ: " + result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static float GetFloat(Scanner in, String output)
    {
        float result;

        while (true)
        {
            System.out.print(output);

            try
            {
                result = in.nextFloat();
                in.nextLine();

                return result;
                
            }
            catch (Exception e)
            {
                in.nextLine();
            }
        }
    }
}