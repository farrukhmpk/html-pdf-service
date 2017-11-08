package org.farrukh.mirza.pdf.service;

import org.apache.commons.lang3.StringUtils;

public abstract class BaseImpl {
	protected String getFormedHTMLWithCSS(String htmlBody, String css) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		if (StringUtils.isNotBlank(css)) {
			sb.append("<style type='text/css'>");
			sb.append(css);
			sb.append("</style>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append(htmlBody);
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}


	protected String correctHtml(String html) {
		html = html.replaceAll("&nbsp;", "&#160;");
//		html = html.replaceAll(" & ", " &amp; ");
		html = html.replaceAll("(&\\w*)(?!&.*;) ", "&amp; ");//Replace &<space> with &amp;

		
		
		return html;
	}

}
