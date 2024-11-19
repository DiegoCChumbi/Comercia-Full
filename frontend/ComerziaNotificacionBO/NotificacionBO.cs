using ComerziaBO;
using ComerziaBO.ComerziaWS;
using System.ComponentModel;

namespace ComerziaNotificacionBO
{
    public class NotificacionBO : BaseBO
    {
        public BindingList<notificacion> listarTodos()
        {
            notificacion[] arreglo = this.WsNotificacionesCliente.listarNotificaciones();

            if (arreglo == null)
            {
                return new BindingList<notificacion>();
            }

            return new BindingList<notificacion>(arreglo);
        }
    }
}
