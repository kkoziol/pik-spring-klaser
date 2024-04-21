package pl.pik.kk.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class NotificationServiceImpl implements NotificationService {

    List<NotificationMessage> msgs = new ArrayList<NotificationMessage>();

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.ERROR, msg);
    }

    @Override
    public List<NotificationMessage> getNotificationMessages() {
        List<NotificationMessage> notifyMessages = msgs;
        msgs = new ArrayList<NotificationMessage>();
        return notifyMessages;
    }

    private void addNotificationMessage(NotificationMessageType type, String msg) {
        msgs.add(new NotificationMessage(type, msg));
    }

}