
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.WarrantyService;
import domain.Warranty;

@Controller
@RequestMapping("/warranty/administrator")
public class WarrantyAdministratorController {

	@Autowired
	private WarrantyService	warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Warranty> warranties;

		warranties = this.warrantyService.findAll();

		result = new ModelAndView("warranty/list");
		result.addObject("warranties", warranties);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int warrantyId) {
		final ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.findOne(warrantyId);
		Assert.notNull(warrantyId);
		result = new ModelAndView("warranty/show");
		result.addObject("warranty", warranty);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int warrantyId) {
		final ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.findOne(warrantyId);
		Assert.notNull(warrantyId);
		result = new ModelAndView("warranty/edit");
		result.addObject("warranty", warranty);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Warranty newWarranty, final BindingResult binding) {
		final ModelAndView result;

		if (!binding.hasErrors()) {
			this.warrantyService.save(newWarranty);
			result = new ModelAndView("warranty/list");
			result.addObject("warranties", this.warrantyService.findAll());
		} else
			result = new ModelAndView("redirect:edit.do?warrantyId=" + newWarranty.getId());

		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public ModelAndView cancel() {
		final ModelAndView result;
		final Collection<Warranty> warranties;

		warranties = this.warrantyService.findAll();

		result = new ModelAndView("warranty/list");
		result.addObject("warranties", warranties);
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int warrantyId) {
		ModelAndView result;
		final Warranty w = this.warrantyService.findOne(warrantyId);
		try {
			this.warrantyService.delete(w);
			result = new ModelAndView("warranty/list");

			result.addObject("warranties", this.warrantyService.findAll());
			return result;
		} catch (final Exception e) {
			result = new ModelAndView("warranty/list");
			result.addObject("exception", e);
			result.addObject("warranties", this.warrantyService.findAll());
			return result;
		}
	}
}