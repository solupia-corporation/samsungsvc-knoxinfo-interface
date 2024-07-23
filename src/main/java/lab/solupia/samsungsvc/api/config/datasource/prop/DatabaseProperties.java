package lab.solupia.samsungsvc.api.config.datasource.prop;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018. 11. 13.
 */
public interface DatabaseProperties {
    public String getDriverClassName();

    public String getUrl();

    public String getUsername();

    public String getPassword();

    public int getInitialSize();

    public int getMaxActive();

    public int getMaxIdle();

    public int getMinIdle();

    public int getMaxWait();

    public String getValidationQuery();
}
