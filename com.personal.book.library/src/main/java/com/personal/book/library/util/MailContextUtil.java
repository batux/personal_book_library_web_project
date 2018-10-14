package com.personal.book.library.util;

import com.personal.book.library.datalayer.entity.Book;
import com.personal.book.library.servicelayer.model.MailContext;

public class MailContextUtil {

	public static MailContext createMailContext(Book book, String toEmails) {
		
		String emailBody = "Merhabalar,\n";
		emailBody += "Kitabınız sisteme eklenmiştir. Özet bilgisi aşağıdaki gibidir.\n";
		emailBody += "\n--- Kitap Bilgileri ---\n";
		emailBody += "Kitap Adı: " + book.getName() + "\n";
		emailBody += "Yazarı: " + book.getAuthorFullName() + "\n";
		emailBody += "Kategorisi: " + book.getCategory().getName() + "\n";
		emailBody += "Keyifli okumalar...\n";
		
		MailContext mailContext = new MailContext("Kitap Ekleme Bilgi", emailBody, toEmails);
		return mailContext;
	}
	
}
