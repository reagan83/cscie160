package cscie160.hw5.rmitest;

public interface Hello extends java.rmi.Remote
{
    String sayHello() throws java.rmi.RemoteException;
}