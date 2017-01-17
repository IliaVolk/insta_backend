import inproject.Application;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations="classpath:test.properties")
public class CrudTest {


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
    Tag unusedTag;
    Place unusedPlace;
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
        unusedTag = new Tag();
        unusedTag.setName("unusedTag");
        unusedTag = tagRepository.save(unusedTag);
        unusedPlace = new Place();
        unusedPlace.setName("unusedPlace");
        unusedPlace = placeRepository.save(unusedPlace);

    }
    @After
    public void after(){

    }
    @Test
    public void testAddPlace(){
        Place place = new Place();
        place.setName("placetoadd");
        place = placeRepository.save(place);
        Place found = placeRepository.findOne(place.getId());
        assertEquals(place, found);


    }
    @Test
    public void testAddTag(){
        Tag tag = new Tag();
        tag.setName("tagtoadd");
        tag = tagRepository.save(tag);
        Tag found  = tagRepository.findOne(tag.getId());
        assertEquals(tag, found);
    }
    @Test
    public void testAddStore(){
        Store store = new Store();
        store.setName("storeToAdd");
        store.setPlace(place1);
        store.setTags(new HashSet<>(Arrays.asList(tag1, tag2)));
        store = storeRepository.save(store);
        Store found = storeRepository.findOne(store.getId());
        assertEquals(store, found);
        assertEquals(store.getPlace(), found.getPlace());
        assertEquals(store.getTags(), found.getTags());
    }
    @Test
    public void testUpdateTag(){
        tag1.setName("updated");
        tag1 = tagRepository.save(tag1);
        Tag found = tagRepository.findOne(tag1.getId());
        assertEquals(tag1, found);
    }
    @Test
    public void testUpdatePlace(){
        place1.setName("updated");
        place1 = placeRepository.save(place1);
        Place found = placeRepository.findOne(place1.getId());
        assertEquals(place1, found);
    }
    @Test
    public void testUpdateStore(){
        store1.setName("newName");
        store1.setUrl("newUrl");
        store1.setImage("newImage");
        store1.setPlace(place2);
        store1.setTags(new HashSet<>(Arrays.asList(tag2, tag3)));
        store1 = storeRepository.save(store1);
        Store found = storeRepository.findOne(store1.getId());
        assertEquals(store1, found);
        assertEquals(store1.getPlace(), found.getPlace());
        assertEquals(store1.getTags(), found.getTags());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testDeleteUsedTag(){
        tagRepository.delete(tag1);
    }
    @Test
    public void testDeleteUnusedTag(){
        assertEquals(unusedTag, tagRepository.findOne(unusedTag.getId()));
        assertEquals(4, tagRepository.findAll().size());
        tagRepository.delete(unusedTag);
        assertNull(tagRepository.findOne(unusedTag.getId()));
        assertEquals(3, tagRepository.findAll().size());
    }
    @Test
    public void testDeleteUnusedPlace(){
        assertEquals(unusedPlace, placeRepository.findOne(unusedPlace.getId()));
        assertEquals(3, placeRepository.findAll().size());
        placeRepository.delete(unusedPlace);
        assertNull(placeRepository.findOne(unusedPlace.getId()));
        assertEquals(2, placeRepository.findAll().size());
    }
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testDeleteUsedPlace(){
        placeRepository.delete(place1);
    }
    @Test
    public void testDeleteStore(){
        assertEquals(store1, storeRepository.findOne(store1.getId()));
        assertEquals(4, storeRepository.findAll().size());
        storeRepository.delete(store1);
        Store found = storeRepository.findOne(store1.getId());
        assertNull(found);
        assertEquals(tag1, tagRepository.findOne(tag1.getId()));
        assertEquals(tag2, tagRepository.findOne(tag2.getId()));
        assertEquals(place1, placeRepository.findOne(place1.getId()));
        assertEquals(3, storeRepository.findAll().size());
    }

}
