package org.jxjz.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XMLUtil
{

	public static Map buildMap(String xmlStr)
	{
		InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
		SAXBuilder saxBuilder = new SAXBuilder();
		try
		{
			Document doc = saxBuilder.build(is);
			Element root = doc.getRootElement();
			return buildMap(root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static Map buildMap(Element ele)
	{
		Map map = new HashMap();
		List attrs = ele.getAttributes();
		for (int i = 0; i < attrs.size(); i++)
		{
			Attribute attr = (Attribute) attrs.get(i);
			map.put(attr.getName(), attr.getValue());
		}

		List children = ele.getChildren();
		String eleName = ele.getName();
		if (children.size() == 0)
		{
			String text = ele.getText();
			if (StringUtils.isNotBlank(text))
			{
				map.put(eleName, text);
			}
		}
		else
		{
			for (int i = 0; i < children.size(); i++)
			{
				Element child = (Element) children.get(i);
				Map childMap = buildMap(child);
				String childName = child.getName();

				if (map.containsKey(childName))
				{
					Object obj = map.get(childName);
					if (obj instanceof Map)
					{
						List list = new Vector();
						list.add(obj);
						list.add(childMap);
						map.remove(childName);
						map.put(childName, list);
					}
					else if (obj instanceof List)
					{
						((List) obj).add(childMap);
					}
				}
				else
				{
					if (childMap.get(childName) instanceof String)
						map.putAll(childMap);
					else map.put(childName, childMap);
				}
			}
		}
		return map;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(new File(
					"D:\\JHP_INTERFACE\\dataset.xml"));
			Element ele = doc.getRootElement();
			Map map = buildMap(ele);
			Map dataset = (Map) map.get("dataset");
			System.out.println(dataset);
			List data = (List) dataset.get("data");
			for (int i = 0; i < data.size(); i++)
			{
				Map map1 = (Map) data.get(i);
				System.out.println(map1);
			}
			//
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}

	}
}
