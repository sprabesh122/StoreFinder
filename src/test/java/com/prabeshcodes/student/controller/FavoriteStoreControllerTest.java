package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.controller.FavoriteStoreController;
import com.prabeshcodes.student.model.FavoriteStore;
import com.prabeshcodes.student.service.FavoriteStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FavoriteStoreControllerTest {

    @Mock
    private FavoriteStoreService favoriteStoreService;

    @InjectMocks
    private FavoriteStoreController favoriteStoreController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteStoreController).build();
    }

    @Test
    public void testAddFavoriteStore() throws Exception {
        FavoriteStore favoriteStore = new FavoriteStore();
        favoriteStore.setId(1L);

//        when(favoriteStoreService.addFavoriteStore(any(FavoriteStore.class))).thenReturn("New Favorite Store is Added");

        mockMvc.perform(post("/favorite-stores/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("New Favorite Store is Added"));

        verify(favoriteStoreService, times(1)).addFavoriteStore(any(FavoriteStore.class));
    }

}