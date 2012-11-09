package com.oilchem.trade.service;

import com.oilchem.trade.dao.BaseDao;
import com.oilchem.trade.dao.map.AbstractTradeDetailRowMapper;
import com.oilchem.trade.dao.map.MyRowMapper;
import com.oilchem.trade.domain.abstrac.TradeSum;
import com.oilchem.trade.domain.abstrac.IdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-5
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
public interface CommonService {

    /**
     * 上传文件
     * @author wei.luo
     * @createTime 2012-11-7
     * @param file  	MultipartFile的文件
     * @param realDir	目标目录的物理路径
     * @return		返回上传之后文件的url
     */
    String uploadFile(MultipartFile file,String realDir);

    /**
     * 解包
     * @param packageSource    源zip文件绝对路径
     * @param unPackageDir     解压目录
     * @return   解包后的文件路径
     */
    String unpackageFile(String packageSource, String unPackageDir);

    /**
     * 导入查询条件表
     * @param jdbcTemplate
     * @param sql
     * @return
     */
    Boolean importCriteriaTab(JdbcTemplate jdbcTemplate,String sql);

    /**
     * 获得有效的查询条件表的记录List
     * @param jdbcTemplate   jdbcTemplate
     * @param dao    mondel dao
     * @param idEntityClass     model bean
     * @param sql  jdbcTemplate's query sql
     * @param filedName  access table's filed name
     * @param <E>   idEntity
     * @return      idEntity列表
     */
    <E extends IdEntity> List<E> queryCriteriaRecord(JdbcTemplate jdbcTemplate,
                final Repository<E,Long> dao, final Class<E> idEntityClass,
                String sql, final String filedName);

    /**
     * 导入贸易明细
     * @param crudRepository  crudRepository与baseDaoDao传相同对象
     * @param baseDaoDao        crudRepository与baseDaoDao传相同对象
     * @param jdbcTemplate       jdbcTemplate
     * @param tradeDetailMapper      tradeDetailMapper
     * @param yearMonth           yearMonth
     * @param sql       sql
     * @param <T>       ImpTradeDetailRowMapper / ExpTradeDetailRowMapper
     * @return
     */
    <T extends AbstractTradeDetailRowMapper> Boolean importTradeDetail(
            CrudRepository crudRepository,BaseDao baseDaoDao,JdbcTemplate jdbcTemplate,
            T tradeDetailMapper,Date yearMonth, String sql) ;

    /**
     * 导入Excel
     * @param repository  tradeSum Dao，总表持久类
     * @param excelSource   excel文件源目录
     * @param tradeSumClass      tradeSum Class
     * @param tradeSumRowMapClass      tradeSumRowMap Class
     * @param yearMonth     数据所在年月
     * @param productType   产品类型
     * @param <E>             ImpTradeSum / ExpTradeSum
     * @param <M>             ImpTradeSumRowMapper / ExpTradeSumRowMapper
     * @return         成功或失败
     */
    <E extends TradeSum,M extends MyRowMapper<E>>
        Boolean importExcel(CrudRepository<E,Long> repository,
                        String excelSource,
                        Class<E> tradeSumClass,
                        Class<M> tradeSumRowMapClass,
                        Date yearMonth,
                        String productType);

    /**
     * 获得未解压的文件列表
     * @return  返回记录的Id与包的全路径组成的Map
     */
    Map<Long,String> getUnExtractPackage(String packageType);

    /**
     * 获得未导入的文件列表
     * @param fileType 文件类型
     * @return   返回记录的Id与文件的全路径组成的Map
     */
    Map<Long,String> getUnImportFile(String fileType);

    /**
     * 获得Model的list列表
     * @param tClass  tClass
     * @param <T>
     * @return
     */
    <T extends IdEntity> List<T> getModelList(Class<T> tClass);
}
