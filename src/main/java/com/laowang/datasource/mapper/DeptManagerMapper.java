package com.laowang.datasource.mapper;

import com.laowang.datasource.beans.DeptManager;
import com.laowang.datasource.beans.DeptManagerExample;
import com.laowang.datasource.beans.DeptManagerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int countByExample(DeptManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int deleteByExample(DeptManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(DeptManagerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int insert(DeptManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int insertSelective(DeptManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    List<DeptManager> selectByExample(DeptManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    DeptManager selectByPrimaryKey(DeptManagerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DeptManager record, @Param("example") DeptManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DeptManager record, @Param("example") DeptManagerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DeptManager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DeptManager record);
}