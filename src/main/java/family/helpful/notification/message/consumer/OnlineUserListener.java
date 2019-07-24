package family.helpful.notification.message.consumer;


import family.helpful.notification.actions.EnumActionStatus;
import family.helpful.notification.message.model.Channel;
import family.helpful.notification.message.model.ChannelContent;
import family.helpful.notification.message.model.User;
import family.helpful.notification.message.model.UserJoinMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@EnableBinding({Sink.class})
public class OnlineUserListener
{
    private static final Logger logger = LoggerFactory.getLogger(ChannelListener.class);
    @Autowired
    private SimpMessagingTemplate template;

    private static HashMap <String, List<User>> onlineUserMap= new HashMap<>();
    private static long id=3;

    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'userLoggedIn'")
    public void userLoggedIn(Message<UserJoinMessage> userJoinMessageMessageEnv) {
        UserJoinMessage userJoinMessage= userJoinMessageMessageEnv.getPayload();

        logger.info(" received userLoggedIn message ["+userJoinMessage.getUser().username );

        User user= userJoinMessage.getUser();

        user.setId(++id);

        String channelName= userJoinMessage.getChannelName();

        List<User> onlineUserList= onlineUserMap.get(channelName);
        if(onlineUserList==null){
            onlineUserList= new ArrayList();

        }
        User filterUser = onlineUserList.stream()
                .filter(filterUserInternal -> user.getUsername().equals(filterUserInternal.getUsername()))
                .findAny()
                .orElse(null);
        if(filterUser==null){
            onlineUserList.add(user);

        }

        onlineUserMap.put(channelName, onlineUserList);

        Message resultMessage= MessageBuilder.withPayload(onlineUserList)
                .setHeader("userLoggedIn"
                        , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationUserLoggedIn", resultMessage);


    }
}
