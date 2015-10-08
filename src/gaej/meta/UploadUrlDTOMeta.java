package gaej.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-10-06 14:06:54")
/** */
public final class UploadUrlDTOMeta extends org.slim3.datastore.ModelMeta<gaej.model.UploadUrlDTO> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<gaej.model.UploadUrlDTO, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<gaej.model.UploadUrlDTO, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<gaej.model.UploadUrlDTO> uploadUrl = new org.slim3.datastore.StringAttributeMeta<gaej.model.UploadUrlDTO>(this, "uploadUrl", "uploadUrl");

    private static final UploadUrlDTOMeta slim3_singleton = new UploadUrlDTOMeta();

    /**
     * @return the singleton
     */
    public static UploadUrlDTOMeta get() {
       return slim3_singleton;
    }

    /** */
    public UploadUrlDTOMeta() {
        super("UploadUrlDTO", gaej.model.UploadUrlDTO.class);
    }

    @Override
    public gaej.model.UploadUrlDTO entityToModel(com.google.appengine.api.datastore.Entity entity) {
        gaej.model.UploadUrlDTO model = new gaej.model.UploadUrlDTO();
        model.setKey(entity.getKey());
        model.setUploadUrl((java.lang.String) entity.getProperty("uploadUrl"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        gaej.model.UploadUrlDTO m = (gaej.model.UploadUrlDTO) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("uploadUrl", m.getUploadUrl());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        gaej.model.UploadUrlDTO m = (gaej.model.UploadUrlDTO) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        gaej.model.UploadUrlDTO m = (gaej.model.UploadUrlDTO) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(gaej.model.UploadUrlDTO) is not defined.");
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        gaej.model.UploadUrlDTO m = (gaej.model.UploadUrlDTO) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getUploadUrl() != null){
            writer.setNextPropertyName("uploadUrl");
            encoder0.encode(writer, m.getUploadUrl());
        }
        writer.endObject();
    }

    @Override
    protected gaej.model.UploadUrlDTO jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        gaej.model.UploadUrlDTO m = new gaej.model.UploadUrlDTO();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("uploadUrl");
        m.setUploadUrl(decoder0.decode(reader, m.getUploadUrl()));
        return m;
    }
}