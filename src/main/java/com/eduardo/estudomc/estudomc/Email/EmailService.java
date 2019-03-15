package com.eduardo.estudomc.estudomc.Email;

import com.eduardo.estudomc.estudomc.pedido.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);
}
