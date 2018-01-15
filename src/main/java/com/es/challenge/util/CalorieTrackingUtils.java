package com.es.challenge.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.es.challenge.CalorieTrackingConstant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CalorieTrackingUtils implements CalorieTrackingConstant {

	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	/**
	 * This is a help to map a list of source to a list of destination
	 * @param fromList
	 * @param toClass
	 * @return
	 */
	public <F,T> List<T> mapList(List<F> fromList, final Class<T> toClass) {
	    return fromList.stream()
	            .map(from -> this.dozerBeanMapper.map(from, toClass))
	            .collect(Collectors.toList());
	}
	
	/**
	 * This is to help map a source to a destination
	 * @param from
	 * @param toClass
	 * @return
	 */
	public <T> T map(Object from, Class<T> toClass) {
		return this.dozerBeanMapper.map(from, toClass);
	}
	
	/**
	 * This is to get Today as Date
	 * @return
	 */
	public static Date getTodayDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * This is to convert String to Date
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		if (Strings.isEmpty(date)) return null;
		try {
			return formatter.parse(date);
		}
		catch (Exception e) {
			log.error("There is an exception when parsing {}", date, e);
			return null;
		}
	}
	
	/**
	 * This is from Date to String
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return displayFormatter.format(date);
	}
}
