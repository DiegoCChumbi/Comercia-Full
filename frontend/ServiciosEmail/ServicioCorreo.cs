using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using MimeKit;
using MailKit.Net.Smtp;
using System.Security.Authentication; // Para SmtpClient de MailKit

namespace ServiciosEmail
{
    public class ServicioCorreo
    {
        public void EnviarCorreoConMailKit(string destinatario, string asunto, string cuerpo)
        {
            Console.WriteLine(destinatario);
            try
            {
                // Crear el mensaje
                var mensaje = new MimeMessage();
                mensaje.From.Add(new MailboxAddress("Comerzia", "sistemcomerzia@gmail.com"));
                mensaje.To.Add(new MailboxAddress("", destinatario));
                mensaje.Subject = asunto;

                // Configurar el cuerpo del mensaje (puede ser HTML)
                mensaje.Body = new TextPart("html")
                {
                    Text = cuerpo
                };

                using (var cliente = new MailKit.Net.Smtp.SmtpClient())
                {
                    //cliente.ServerCertificateValidationCallback = (s, c, h, e) => true; // Evitar problemas con certificados
                    cliente.Connect("smtp.gmail.com", 587, MailKit.Security.SecureSocketOptions.StartTls);

                    try
                    {
                        // Autenticación
                        cliente.Authenticate("sistemcomerzia@gmail.com", "ycuf obwk txxy zjzq"); 

                        // Enviar el mensaje
                        cliente.Send(mensaje);
                        cliente.Disconnect(true); // Cerrar la conexión
                    }
                    catch (AuthenticationException authEx)
                    {
                        Console.WriteLine($"Error de autenticación: {authEx.Message}");
                    }
                }

                Console.WriteLine("Correo enviado exitosamente.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al enviar el correo: {ex.Message}");
            }
        }
    }
}
