package com.rapid.vit.email;



//@Service
//public class EmailSenderService implements EmailSender{
//    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    @Async
//    public void sendEmail(String from, String to, String subject, String email) {
//        LOGGER.info("This is inside of the send email and to = {}, subject={}, email={}", to, subject, email);
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//
//            LOGGER.error("trying to send email and helper = {}",helper);
//            mailSender.send(mimeMessage);
//            LOGGER.error("email was sent and {}", mimeMessage.toString());
//        } catch (MessagingException e) {
//            LOGGER.error("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//        }
//    }
//}
