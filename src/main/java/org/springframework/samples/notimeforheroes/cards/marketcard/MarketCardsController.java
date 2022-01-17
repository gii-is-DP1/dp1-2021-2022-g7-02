package org.springframework.samples.notimeforheroes.cards.marketcard;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cards/market")
public class MarketCardsController {

	public static final String MARKET_CARD_LISTING = "cards/market/marketCardsListing";
	public static final String MARKET_CARD_FORM =  "cards/market/createOrUpdateMarketCardsForm";
	
	@Autowired
	MarketCardsService MarketService;
	
	@GetMapping("/{pageNo}")
	public String getAll(ModelMap model, @PathVariable("pageNo") Integer pageNo){
		Collection<MarketCard> lista = MarketService.findAllPage(pageNo, 5);
		model.addAttribute("market", lista);
		model.addAttribute("pag", pageNo);

		return MARKET_CARD_LISTING;
	}
	
	@GetMapping
	public String listMarketCard(ModelMap model) {
		model.addAttribute("market", MarketService.findAll());
		return MARKET_CARD_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editMarketCard(ModelMap model, @PathVariable("id") int id) {
		Optional<MarketCard> market = MarketService.findById(id);
		if(market.isPresent()) {
			model.addAttribute("market", market.get());
			return MARKET_CARD_FORM;
		} else {
			model.addAttribute("message", "This Market card doesn't exist");
			return listMarketCard(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editMarketCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid MarketCard modifiedMarket, BindingResult result) {
		Optional<MarketCard> market = MarketService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The Market card has errors");
			return MARKET_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedMarket, market.get(), "id");
			model.addAttribute("market", market.get());
			listMarketCard(model);
			redirect.addFlashAttribute("message", "Market card modified");
			return "redirect:/cards/market";
		}
	}
	
	@GetMapping("/new")
	public String newMarketCard(Map<String, Object> map) {
		MarketCard market= new MarketCard();
		map.put("market", market);
		return MARKET_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newMarketCard(RedirectAttributes redirect, @Valid MarketCard market,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return MARKET_CARD_FORM;
		} else {
			MarketService.saveMarketCard(market);
			model.addAttribute("message", "Market card created");
			listMarketCard(model);
			redirect.addFlashAttribute("message", "Market card created");
			return "redirect:/cards/market";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteMarketCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<MarketCard> market = MarketService.findById(id);
		MarketService.deleteMarketCard(market.get());
		listMarketCard(model);
		redirect.addFlashAttribute("message", "Market card deleted");
		return "redirect:/cards/market";

	}
	
}
