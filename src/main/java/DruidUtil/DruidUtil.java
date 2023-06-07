package DruidUtil;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DruidUtil {

    private static final DataSource dataSource;

    static{

        try {

            Properties properties = new Properties();

            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("./druid.properties");

            properties.load(is);

            dataSource = DruidDataSourceFactory.createDataSource(properties);

            if( is != null){

                is.close();

            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public static DataSource getDataSource(){

        return dataSource;//返回dataSource

    }

}
