package com.sip.controllers;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.entities.Article;
import com.sip.entities.Provider;
import com.sip.repositories.ArticleRepository;
import com.sip.repositories.ProviderRepository;
@Controller
@RequestMapping("/article/")
public class ArticleController {
	static String trouve=null;
	
	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository) {
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
    }
    
    @GetMapping("list")
    public String listProviders(Model model) {
    	//model.addAttribute("articles", null);
    	List<Article> la = (List<Article>)articleRepository.findAll();
    	if(la.size()==0)
    		la = null;
        model.addAttribute("articles", la);
        model.addAttribute("trouve", trouve); 
        return "article/listArticles";
    }
    
    @GetMapping("add")
    public String showAddArticleForm(Model model) {
    	
    	model.addAttribute("providers", providerRepository.findAll());
    	model.addAttribute("article", new Article());
        return "article/addArticle";
    }
    
    @PostMapping("add")
    //@ResponseBody
    public String addArticle(@Valid Article article, BindingResult result, @RequestParam(name = "providerId", required = true) Long p) {
    	
    	Provider provider = providerRepository.findById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + p));
    	article.setProvider(provider);
    	
    	 articleRepository.save(article);
    	 return "redirect:list";
    	
    	//return article.getLabel() + " " +article.getPrice() + " " + p.toString();
    }
    
    @GetMapping("delete/{id}")
    public String deleteProvider(@PathVariable("id") long id, Model model) {
        /*Article artice = articleRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));*/
    	
    	
    	
        Optional<Article>article = articleRepository.findById(id);
        
        if(article.isPresent())
        {
        	 articleRepository.delete(article.get());
        	 trouve="existe";
        }
        else {  // le problème
        	trouve="inexiste";
        	
        }
     // model.addAttribute("trouve", trouve); 
      
  	//List<Article> la = (List<Article>)articleRepository.findAll();
	//if(la.size()==0)
	//	la = null;
    //model.addAttribute("articles", la);
   // return "article/listArticles";
    
    
      return "redirect:../list";
    }
    
    @GetMapping("edit/{id}")
    public String showArticleFormToUpdate(@PathVariable("id") long id, Model model) {
    	Article article = articleRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
    	
        model.addAttribute("article", article);
        model.addAttribute("providers", providerRepository.findAll());
        model.addAttribute("idProvider", article.getProvider().getId());
        
        return "article/updateArticle";
    }
    @PostMapping("edit")
    public String updateArticle( @Valid Article article, BindingResult result,
        Model model, @RequestParam(name = "providerId", required = false) Long p) {
        if (result.hasErrors()) {
        	
            return "article/updateArticle";
        }
        
        Provider provider = providerRepository.findById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + p));
        
    	article.setProvider(provider);
    	
        articleRepository.save(article);
        return "redirect:list";
    }

}
