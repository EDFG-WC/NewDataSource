package com.laowang.datasource.mapper;

import com.laowang.datasource.beans.CountryLanguage;
import com.laowang.datasource.beans.CountryLanguageExample;
import com.laowang.datasource.beans.CountryLanguageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountryLanguageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int countByExample(CountryLanguageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int deleteByExample(CountryLanguageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(CountryLanguageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int insert(CountryLanguage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int insertSelective(CountryLanguage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    List<CountryLanguage> selectByExample(CountryLanguageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    CountryLanguage selectByPrimaryKey(CountryLanguageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CountryLanguage record, @Param("example") CountryLanguageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CountryLanguage record, @Param("example") CountryLanguageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CountryLanguage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table countrylanguage
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CountryLanguage record);
}