package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.dto.TagDto;
import com.softserve.actent.model.entity.Tag;
import com.softserve.actent.repository.*;
import com.softserve.actent.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TagController.class)
@AutoConfigureMockMvc
public class TagControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TagService tagService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ChatRepository chatRepository;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private EquipmentRepository equipmentRepository;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private EventUserRepository eventUserRepository;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private RegionRepository regionRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private TagRepository tagRepository;

    @MockBean
    private UserRepository userRepository;

    private final Long firstTagId = 1L;
    private final Long secondTagId = 2L;
    private final Long nonExistingTagId = 99L;
    private final String firstTagText = "beer";
    private final String secondTagText = "football";
    private final String nonExistingText = "sport";
    private Tag firstTag;
    private Tag secondTag;
    private TagDto firstTagDto;
    private TagDto secondTagDto;
    private List<Tag> tags;

    @Before
    public void setUp() {

        firstTag = new Tag();
        firstTag.setId(firstTagId);
        firstTag.setText(firstTagText);

        secondTag = new Tag();
        secondTag.setId(secondTagId);
        secondTag.setText(secondTagText);

        firstTagDto = new TagDto(firstTagId, firstTagText);
        secondTagDto = new TagDto(secondTagId, secondTagText);

        tags = Arrays.asList(firstTag, secondTag);

        given(tagService.add(firstTag)).willReturn(firstTag);
        given(tagService.add(secondTag)).willReturn(secondTag);

        given(tagService.get(firstTagId)).willReturn(firstTag);
        given(tagService.get(secondTagId)).willReturn(secondTag);
        given(tagService.get(nonExistingTagId)).willThrow(new ResourceNotFoundException(
                ExceptionMessages.TAG_NOT_FOUND_WITH_ID, ExceptionCode.NOT_FOUND));

        given(tagService.getByText(firstTagText)).willReturn(firstTag);
        given(tagService.getByText(secondTagText)).willReturn(secondTag);
        given(tagService.getByText(nonExistingText)).willThrow(new ResourceNotFoundException(
                ExceptionMessages.TAG_NOT_FOUND_WITH_TEXT, ExceptionCode.NOT_FOUND));

        given(tagService.getAll()).willReturn(tags);

        given(tagService.update(firstTag, firstTagId)).willReturn(firstTag);
        given(tagService.update(secondTag, secondTagId)).willReturn(secondTag);
        given(tagService.update(firstTag, nonExistingTagId)).willThrow(new ResourceNotFoundException(
                ExceptionMessages.TAG_NOT_FOUND_WITH_ID, ExceptionCode.NOT_FOUND));
        given(tagService.update(secondTag, nonExistingTagId)).willThrow(new ResourceNotFoundException(
                ExceptionMessages.TAG_NOT_FOUND_WITH_ID, ExceptionCode.NOT_FOUND));

        Mockito.doNothing().when(tagService).delete(firstTagId);
        Mockito.doNothing().when(tagService).delete(secondTagId);

        given(modelMapper.map(firstTag, TagDto.class)).willReturn(firstTagDto);
        given(modelMapper.map(secondTag, TagDto.class)).willReturn(secondTagDto);
        given(modelMapper.map(firstTagDto, Tag.class)).willReturn(firstTag);
        given(modelMapper.map(secondTagDto, Tag.class)).willReturn(secondTag);
    }

    @Test
    public void whenGetTagByExistingId_thenReturnTagJson() throws Exception {

        String url = UrlConstants.API_V1 + "/tags/" + firstTagId;
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(firstTagId))
                .andExpect(jsonPath("$.text").value(firstTagText));
    }

    @Test
    public void whenGetAllTags_thenReturnTagsListJson() throws Exception {

        String url = UrlConstants.API_V1 + "/tags";
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tags.size())))
                .andExpect(jsonPath("$[0].id").value(firstTagId))
                .andExpect(jsonPath("$[0].text").value(firstTagText))
                .andExpect(jsonPath("$[1].id").value(secondTagId))
                .andExpect(jsonPath("$[1].text").value(secondTagText));
    }

    @After
    public void tearDown() throws Exception {
        firstTag = null;
        secondTag = null;
        firstTagDto = null;
        secondTagDto = null;
        tags = null;
    }
}
