package nogikoi.erkikt.rhcloud.com.app.nogiitem;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nogikoi.erkikt.rhcloud.com.domain.model.NogiItem;
import nogikoi.erkikt.rhcloud.com.domain.service.nogiitem.NogiItemService;

@Controller
@RequestMapping("nogiitem")
public class NogiItemController {

    @Inject
    NogiItemService nogiItemService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public NogiItemForm setUpForm() {
        NogiItemForm form = new NogiItemForm();
        form.setCount(1);
        return form;
    }

    @RequestMapping("list")
    public String list(@AuthenticationPrincipal User principal, Model model) {
        List<NogiItem> nogiItems = nogiItemService.getAllNogiItems(principal.getUsername());
        model.addAttribute("nogiItems", nogiItems);
        return "nogiitem/NogiItemList";
    }

    @RequestMapping(path = "plus", method = RequestMethod.POST)
    @ResponseBody
    public Integer plus(@RequestParam Integer id, Model model) {
        nogiItemService.plusCount(id);
        NogiItem nogiItem = nogiItemService.getNogiItem(id);
        return nogiItem.getCount();
        // return "redirect:/nogiitem/list";
    }

    @RequestMapping(path = "minus", method = RequestMethod.POST)
    @ResponseBody
    public Integer minus(@RequestParam Integer id, Model model) {
        nogiItemService.minusCount(id);
        NogiItem nogiItem = nogiItemService.getNogiItem(id);
        return nogiItem.getCount();
        // return "redirect:/nogiitem/list";
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(@RequestParam Integer id, Model model) {
        return nogiItemService.deleteNogiItem(id);
        // return "redirect:/nogiitem/list";
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public String add(@Validated NogiItemForm form, BindingResult result, @AuthenticationPrincipal User principal,
            Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            attrs.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "nogiItemForm", result);
            attrs.addFlashAttribute("nogiItemForm", form);
            return "redirect:/nogiitem/list";
        }
        NogiItem nogiItem = new NogiItem();
        nogiItem.setName(form.getName());
        nogiItem.setCount(form.getCount());
        nogiItem.setUserId(principal.getUsername());
        nogiItemService.addNogiItem(nogiItem);
        return "redirect:/nogiitem/list";
    }

}
