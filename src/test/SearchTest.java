import inproject.Application;
import inproject.controller.StoreController;
import inproject.entity.Place;
import inproject.entity.Store;
import inproject.entity.Tag;
import inproject.repository.PlaceRepository;
import inproject.repository.StoreRepository;
import inproject.repository.TagRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class SearchTest {

    @Autowired
    StoreController storeController;

    @Autowired
    TagRepository tagRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    PlaceRepository placeRepository;
    Tag tag1;
    Tag tag2;
    Tag tag3;
    Place place1;
    Place place2;
    Store store1;
    Store store2;
    Store store3;
    Store store4;
    @Before
    public void before(){
        storeRepository.deleteAll();
        tagRepository.deleteAll();
        placeRepository.deleteAll();
        tag1 = new Tag();
        tag1.setName("tag1");
        tag1 = tagRepository.save(tag1);
        tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagRepository.save(tag2);
        tag3 = new Tag();
        tag3.setName("tag3");
        tag3 = tagRepository.save(tag3);
        place1 = new Place();
        place1.setName("place1");
        place1 = placeRepository.save(place1);
        place2 = new Place();
        place2.setName("place2");
        place2 = placeRepository.save(place2);
        store1 = new Store();
        store1.setName("store1");
        store1.setTags(new HashSet<>(Arrays.asList(tag1, tag2)));
        store1.setPlace(place1);
        store1 = storeRepository.save(store1);
        store2 = new Store();
        store2.setName("store2");
        store2.setTags(new HashSet<>(Arrays.asList(tag1)));
        store2.setPlace(place1);
        store2 = storeRepository.save(store2);
        store3 = new Store();
        store3.setName("store3");
        store3.setTags(new HashSet<>(Arrays.asList(tag2)));
        store3.setPlace(place2);
        store3 = storeRepository.save(store3);
        store4 = new Store();
        store4.setName("store4");
        store4.setTags(new HashSet<>(Arrays.asList(tag3)));
        store4.setPlace(place2);
        store4 = storeRepository.save(store4);

    }
    @After
    public void after(){

    }
    @Test
    public void testNoParameters() throws Exception {
        List<Store> stores =  storeController.search(null, null);
        assertEquals(Arrays.asList(store1, store2, store3, store4), stores);
    }
    @Test
    public void testPlace() throws Exception{
        String placeName1 = place1.getName();
        String placeName2 = place2.getName();
        List<Store> stores = storeController.search(null, placeName1);
        assertEquals(Arrays.asList(store1, store2), stores);

        stores = storeController.search(null, placeName2);
        assertEquals(Arrays.asList(store3, store4), stores);
    }
    @Test
    public void testTags() throws Exception{
        String tagString1 = tag1.getName();
        List<Store> stores = storeController.search(tagString1, null);
        assertEquals(stores, Arrays.asList(store1, store2));

        String tagString2 = tag1.getName()+","+tag2.getName();
        stores = storeController.search(tagString2, null);
        assertEquals(store1, stores.get(0));
        assertEquals(Arrays.asList(store1, store3, store2), stores);//непонятно почему сначала 3 а потом 2
    }
    @Test
    public void testTagsPlace() throws Exception{
        String tagString1 = tag3.getName()+","+tag2.getName();
        List<Store> stores = storeController.search(tagString1, place2.getName());
        assertEquals(Arrays.asList(store3, store4), stores);
    }

}