using SoftComerziaBO.ComerziaWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftComerziaBO
{
    public class BaseBO
    {
        private ComerziaWeb.AlmacenWSClient wsCliente;

        public BaseBO()
        {
            this.WsCliente = new ComerziaWeb.AlmacenWSClient();
        }

        public AlmacenWSClient WsCliente { get => wsCliente; set => wsCliente = value; }
    }
}
