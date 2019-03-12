package com.softserve.actent.constant;

public class ExceptionMessages {

    public static final String EVENT_BY_THIS_ID_IS_NOT_FOUND = "Event by this id is not found";
    public static final String EVENT_BY_THIS_TITLE_IS_NOT_FOUND = "Event by this title is not found";

    public static final String EQUIPMENT_BY_THIS_ID_IS_NOT_FOUND = "Equipment by this id is not found";
    public static final String EQUIPMENTS_ARE_NOT_FOUND = "Equipments are not found";

    public static final String USER_BY_THIS_ID_IS_NOT_FOUND = "User by this id is not found";
  
    public static final String NO_REVIEW_TEXT = "You need to provide review text.";
    public static final String NO_REVIEW_SCORE = "You need to provide review score.";
    public static final String BAD_REVIEW_SCORE = "Review score must be in range from 1 to 5.";
    public static final String NO_REVIEW_WITH_ID = "Review with requested id was not found.";

    public static final String NO_IMAGE_FILEPATH = "You need to provide image url.";
    public static final String INAPPROPRIATE_HASH_LENGTH = "SHA256 hash must be exactly 64 symbols in length.";
    public static final String NO_IMAGE_WITH_ID = "Image with requested id was not found.";
    public static final String NO_IMAGE_WITH_HASH = "Image with requested hash was not found.";
    public static final String NO_IMAGE_WITH_PATH = "Image with requested path was not found.";

    public static final String NO_TAG_TEXT = "You need to provide tag text.";
    public static final String TOO_SHORT_TAG_TEXT = "Tag must be at least three symbols long.";
    public static final String NO_TAG_WITH_ID = "Tag with requested id was not found.";
    public static final String NO_TAG_WITH_TEXT = "Tag with requested text was not found.";

    public static final String CHAT_BY_THIS_ID_IS_NOT_FOUND = "Chat by this id is not found";
    public static final String ACTIVE_BY_THIS_TYPE_IS_NOT_FOUND = "While adding chat to active. Active by this type is not found";
  
    public static final String YOU_CAN_NOT_CHANGE_THIS_MESSAGE = "You can't change this message";
    public static final String MESSAGE_NOT_FOUND = "Message not found";

    public static final String EMAIL_ALREADY_USED = "There is a user with such email!";
    public static final String USER_NOT_REGISTRED = "User not registered!";

}
