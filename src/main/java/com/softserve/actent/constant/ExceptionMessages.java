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

    public static final String TOO_SHORT_TAG_TEXT = "Tag must be at least three symbols long.";
    public static final String NO_TAG_WITH_ID = "Tag with requested id was not found.";
    public static final String NO_TAG_WITH_TEXT = "Tag with requested text was not found.";
}
