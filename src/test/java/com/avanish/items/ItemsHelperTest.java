package com.avanish.items;

import com.avanish.items.config.PropertyConfig;
import com.avanish.items.dao.JdbcRepository;
import com.avanish.items.model.*;
import com.avanish.items.rest.v1.exception.InvalidDataException;
import com.avanish.items.rest.v1.helper.ItemsHelper;
import com.avanish.items.rest.v1.validation.ItemsValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ItemsHelperTest {

    private ItemEligibilityCheckRequest itemEligibilityCheckRequest;
    private SellerRequest sellerRequest;

    @InjectMocks
    private ItemsHelper itemsHelper;

    @Mock
    private JdbcRepository jdbcRepository;

    @Mock
    private ItemsValidator itemsValidator;

    @Mock
    private PropertyConfig propertyConfig;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        getItemEligibilityCheckRequest();
        getSellerRequest();

    }

    private void getSellerRequest() {

        sellerRequest = new SellerRequest();
        sellerRequest.setSeller("A");

    }

    private void getItemEligibilityCheckRequest() {

        itemEligibilityCheckRequest = new ItemEligibilityCheckRequest();
        itemEligibilityCheckRequest.setCategory(10);
        itemEligibilityCheckRequest.setSeller("A");
        itemEligibilityCheckRequest.setPrice(20d);

    }

    @Test
    public void testIsItemEligible_True() {

        Mockito.when(jdbcRepository.isItemEligible(Mockito.anyString(), Mockito.anyInt())).thenReturn(Boolean.TRUE);
        ItemEligibilityCheckResponse itemEligibilityCheckResponse = itemsHelper.isItemEligible(itemEligibilityCheckRequest);
        Assert.assertTrue(itemEligibilityCheckResponse.isEligible());

    }

    @Test
    public void testIsItemEligible_ClientError() {

        itemEligibilityCheckRequest.setSeller("");
        Mockito.when(itemsValidator.validateSeller(Mockito.anyString())).thenThrow(new InvalidDataException("Client Error"));
        ItemEligibilityCheckResponse itemEligibilityCheckResponse = itemsHelper.isItemEligible(itemEligibilityCheckRequest);
        Assert.assertFalse(itemEligibilityCheckResponse.isEligible());
        Assert.assertEquals("Client Error", itemEligibilityCheckResponse.getError().getMessage());

    }

    @Test
    public void testIsApprovedSeller_ServerError() {

        Mockito.when(itemsValidator.validateSeller(Mockito.anyString())).thenThrow(new NullPointerException("Server Error"));
        ItemEligibilityCheckResponse itemEligibilityCheckResponse = itemsHelper.isItemEligible(itemEligibilityCheckRequest);
        Assert.assertFalse(itemEligibilityCheckResponse.isEligible());
        Assert.assertEquals("java.lang.NullPointerException: Server Error", itemEligibilityCheckResponse.getError().getMessage());

    }

    @Test
    public void testAddSeller_Success() {

        AddSellerResponse addSellerResponse = new AddSellerResponse();
        Mockito.when(jdbcRepository.addSeller(Mockito.any())).thenReturn(Boolean.TRUE);
        addSellerResponse = itemsHelper.addSeller(sellerRequest);
        Assert.assertEquals("Successfully added seller A", addSellerResponse.getMessage());
    }

    @Test
    public void testDeleteSellerSuccess() {

        DeleteSellerResponse deleteSellerResponse = new DeleteSellerResponse();
        Mockito.when(jdbcRepository.deleteSeller(Mockito.any())).thenReturn(Boolean.TRUE);
        deleteSellerResponse = itemsHelper.deleteSeller(sellerRequest);
        Assert.assertEquals("Successfully deleted seller A", deleteSellerResponse.getMessage());

    }
}
