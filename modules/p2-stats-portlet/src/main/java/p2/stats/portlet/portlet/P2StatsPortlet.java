package p2.stats.portlet.portlet;

import p2.stats.portlet.constants.P2StatsPortletKeys;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author greg
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=p2-stats-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + P2StatsPortletKeys.P2Stats,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class P2StatsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		int users = userLocalService.getUsersCount();
		System.out.println(users);

		List<Layout> layouts = layoutLocalService.getLayouts(-1, -1);
		System.out.println(layouts);

		List<JournalArticle> articles = journalArticleLocalService.getArticles();
		System.out.println(articles);

		super.doView(renderRequest, renderResponse);


	}

	@Reference
	JournalArticleLocalService journalArticleLocalService;

	@Reference
	LayoutLocalService layoutLocalService;

	@Reference
	UserLocalService userLocalService;
}