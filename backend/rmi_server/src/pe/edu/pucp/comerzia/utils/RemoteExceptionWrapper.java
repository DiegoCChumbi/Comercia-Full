package pe.edu.pucp.comerzia.utils;

import java.rmi.RemoteException;

public class RemoteExceptionWrapper {

  /**
   * Executes the provided action and wraps exceptions in a RemoteException.
   *
   * @param <T> The type of the return value.
   * @param action The action to execute, provided as a lambda or method reference.
   * @return The result of the action.
   * @throws RemoteException If any exception occurs during the action.
   */
  public static <T> T wrap(RemoteAction<T> action) throws RemoteException {
    try {
      return action.execute();
    } catch (Exception ex) {
      throw new RemoteException("Error during remote method execution", ex);
    }
  }

  @FunctionalInterface
  public interface RemoteAction<T> {
    T execute() throws Exception;
  }
}
