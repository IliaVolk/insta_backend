package inproject.entity;


public class StoreSearchResponse implements Comparable<StoreSearchResponse>{
    private Store store;
    private Long count;

    public StoreSearchResponse(Store store, Long count) {
        this.store = store;
        this.count = count;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public int compareTo(StoreSearchResponse o) {
        return this.count > o.count ? -1:
                this.count < o.count? 1:0;
    }
}
