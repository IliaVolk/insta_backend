import inproject.Application;
import inproject.controller.StoreController;
import inproject.controller.TagController;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestTagDelete {

    @Autowired
    StoreController storeController;

    @Autowired
    TagController tagController;

    @Autowired
    TagRepository tagRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    PlaceRepository placeRepository;
    Tag tag1;
    Tag tag2;
    Store store1;
    Store store2;
    @Before
    public void before() throws Exception {
        storeRepository.deleteAll();
        tagRepository.deleteAll();
        placeRepository.deleteAll();
        tag1 = new Tag();
        tag1.setName("tag1");
        tag1 = tagRepository.save(tag1);
        tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagRepository.save(tag2);
        store1 = new Store();
        store1.setName("store1");
        store1.setTags(new HashSet<>(Arrays.asList(tag1, tag2)));
        store1 = storeRepository.save(store1);
        store2 = new Store();
        store2.setName("store2");
        store2.setTags(new HashSet<>(Arrays.asList(tag1)));
        store2 = storeRepository.save(store2);

    }
    @After
    public void after() throws Exception{
    }
    @Test
    public void test(){
        assertEquals(Arrays.asList(store1, store2), storeRepository.findAll());
    }
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testDeleteTag(){
        assertEquals(2, storeRepository.findOne(store1.getId()).getTags().size());
        assertEquals(1, storeRepository.findOne(store2.getId()).getTags().size());
        tagRepository.delete(tag1);
        assertEquals(0, storeRepository.findOne(store2.getId()).getTags().size());
        assertEquals(1, storeRepository.findOne(store1.getId()).getTags().size());
    }
    @Test
    public void testDeleteStore(){
        assertEquals(2, tagRepository.findOne(tag1.getId()).getSize());
        assertEquals(1, tagRepository.findOne(tag2.getId()).getSize());
        storeRepository.delete(store1);
        assertEquals(1, tagRepository.findOne(tag1.getId()).getSize());
        assertEquals(0, tagRepository.findOne(tag2.getId()).getSize());
    }

}