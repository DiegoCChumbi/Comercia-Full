using ComerziaBO.ComerziaWS;

namespace ComerziaBO
{
    public class BaseBO
    {
        private AlmacenWSClient wsGestionAlmacen;
        private RecursosHumanosWSClient wsRecursosHumanos;
        private RelacionesComercialesWSClient wsRelacionesComerciales;
        private GestionComercialWSClient wsGestionComercialCliente;
        private NotificacionesWSClient wsNotificacionesCliente;

        public BaseBO()
        {
            this.WsGestionAlmacen = new AlmacenWSClient();
            this.WsRecursosHumanos = new RecursosHumanosWSClient();
            this.wsRelacionesComerciales = new RelacionesComercialesWSClient();
            this.wsGestionComercialCliente = new GestionComercialWSClient();
            this.wsNotificacionesCliente = new NotificacionesWSClient();
        }

        public AlmacenWSClient WsGestionAlmacen
        {
            get => wsGestionAlmacen;
            set => wsGestionAlmacen = value;
        }
        public RecursosHumanosWSClient WsRecursosHumanos
        {
            get => wsRecursosHumanos;
            set => wsRecursosHumanos = value;
        }
        public RelacionesComercialesWSClient WsRelacionesComerciales
        {
            get => wsRelacionesComerciales;
            set => wsRelacionesComerciales = value;
        }
        public GestionComercialWSClient WsGestionComercialCliente
        {
            get => wsGestionComercialCliente;
            set => wsGestionComercialCliente = value;
        }
        public NotificacionesWSClient WsNotificacionesCliente
        {
            get => wsNotificacionesCliente;
            set => wsNotificacionesCliente = value;
        }
    }
}
