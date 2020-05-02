package com.avanish.items;

import com.avanish.items.model.*;
import com.avanish.items.model.Error;
import com.avanish.items.rest.v1.ItemsResource;
import com.avanish.items.rest.v1.helper.ItemsHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class ItemsResourceTest {

    private ItemEligibilityCheckRequest itemEligibilityCheckRequest;

    private SellerRequest sellerRequest;

    @InjectMocks
    private ItemsResource itemsResource;

    @Mock
    private ItemsHelper itemsHelper;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        getCheckItemEligibilityRequest();
        getAddSellerRequest();
    }

    private void getAddSellerRequest() {

        sellerRequest = new SellerRequest();
        sellerRequest.setSeller("B");

    }

    private void getCheckItemEligibilityRequest() {

        itemEligibilityCheckRequest = new ItemEligibilityCheckRequest();
        itemEligibilityCheckRequest.setTitle("best seller book");
        itemEligibilityCheckRequest.setSeller("A");
        itemEligibilityCheckRequest.setCategory(10);
        itemEligibilityCheckRequest.setPrice(21.99);

    }

    @Test
    public void testCheckItemEligibility_True() {

        ItemEligibilityCheckResponse itemEligibilityCheckResponse = new ItemEligibilityCheckResponse();
        itemEligibilityCheckResponse.setEligible(Boolean.TRUE);
        Mockito.when(itemsHelper.isItemEligible(Mockito.any())).thenReturn(itemEligibilityCheckResponse);
        itemEligibilityCheckResponse = itemsResource.itemEligible(itemEligibilityCheckRequest);
        Assert.assertTrue(itemEligibilityCheckResponse.isEligible());

    }

    @Test
    public void testCheckItemEligibility_False() {

        ItemEligibilityCheckResponse itemEligibilityCheckResponse = new ItemEligibilityCheckResponse();
        itemEligibilityCheckResponse.setEligible(Boolean.FALSE);
        Mockito.when(itemsHelper.isItemEligible(Mockito.any())).thenReturn(itemEligibilityCheckResponse);
        itemEligibilityCheckResponse = itemsResource.itemEligible(itemEligibilityCheckRequest);
        Assert.assertFalse(itemEligibilityCheckResponse.isEligible());

    }

    @Test
    public void testAddSeller_Added() {

        AddSellerResponse addSellerResponse = new AddSellerResponse();
        addSellerResponse.setMessage("Seller Added Successfully");
        Mockito.when(itemsHelper.addSeller(sellerRequest)).thenReturn(addSellerResponse);
        Assert.assertEquals("Seller Added Successfully", addSellerResponse.getMessage());

    }

    @Test
    public void testAddSeller_AlreadyExists() {

        AddSellerResponse addSellerResponse = new AddSellerResponse();
        Error error = new Error();
        error.setMessage("Seller Already Exists");
        addSellerResponse.setError(error);
        Mockito.when(itemsHelper.addSeller(sellerRequest)).thenReturn(addSellerResponse);
        Assert.assertEquals("Seller Already Exists", addSellerResponse.getError().getMessage());

    }

    @Test
    public void testDeleteSeller_Deleted() {

        DeleteSellerResponse deleteSellerResponse = new DeleteSellerResponse();
        deleteSellerResponse.setMessage("Seller Deleted Successfully");
        Mockito.when(itemsHelper.deleteSeller(sellerRequest)).thenReturn(deleteSellerResponse);
        Assert.assertEquals("Seller Deleted Successfully", deleteSellerResponse.getMessage());

    }

    @Test
    public void testDeleteSeller_NotExists() {

        DeleteSellerResponse deleteSellerResponse = new DeleteSellerResponse();
        Error error = new Error();
        error.setMessage("Seller not exists");
        deleteSellerResponse.setError(error);
        Mockito.when(itemsHelper.deleteSeller(sellerRequest)).thenReturn(deleteSellerResponse);
        Assert.assertEquals("Seller not exists", deleteSellerResponse.getError().getMessage());

    }

}
