package com.soulcode.projetofinal.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.soulcode.projetofinal.models.Financeiro;
import com.soulcode.projetofinal.repositories.FinanceiroRepository;

@Controller
public class FinancasController {

    @Autowired
    private FinanceiroRepository FinanceiroRepository;

    @GetMapping("/financas")
    public ModelAndView paginaFinancas(){
        List<Financeiro> financas = FinanceiroRepository.findAll(); //Equivale a SELECT*FROM FINANCAS
        // Usamos ModelAndView quando precisamos fornecer dados para o HTML
        ModelAndView mv = new ModelAndView("financas"); //// Indica qual o template/view
        // Nome da variável do template e o valor dessa variável
        mv.addObject("listaFinancas", financas); //Conecta com o HTML
        return mv; // Objeto configurado com a view e os dados que ela vai usar
    }

     @PostMapping("/financas/delete") // action, method, name
    public String deleteFinancas(@RequestParam Integer id) { // GET-POST-REDIRECT
        // @RequestParam no POST = vai procurar o valor com o nome determinado
        try {
            FinanceiroRepository.deleteById(id);
        } catch (Exception e) {
            // Em caso de algum problema mostra a página de erro
            return "erro";
        }

        // Redireciona o usuário para a lista de finanças
        // após a remoção feita com sucesso
        return "redirect:/financas";
    }

    @PostMapping("/financas/create")
    public String createFinancas(Financeiro financeiro) {
        try {
            FinanceiroRepository.save(financeiro);
        } catch (Exception e) {
            return "erro";
        }

        return "redirect:/financas";
    }
    
    @GetMapping("/financas/{id}/edit")
    public ModelAndView paginaAtualizarFinancas(@PathVariable Integer id){
        Optional<Financeiro> financeiroOpt = FinanceiroRepository.findById(id);

        if (financeiroOpt.isPresent()) {
            Financeiro financeiro = financeiroOpt.get();
            ModelAndView mv = new ModelAndView("financas-atualizar");
            mv.addObject("financeiro", financeiro);
            return mv;
        }else{
            ModelAndView mvErro = new ModelAndView("erro");
            mvErro.addObject("msg", "Lançamento não encontrado. Impossível de editar.");
            return mvErro;
        }
    }
    @PostMapping("/financas/update")
    public String updateFinancas(Financeiro financeiro) {
        // Na ação de atualizar, o ID do cliente atual será enviado junto.
        try {
            Optional<Financeiro> financeiroOpt = FinanceiroRepository.findById(financeiro.getIdCliente());

            if (financeiroOpt.isPresent()) {
                // Antes de efetuar a operação, será checado o campo ID.
                // Se houver um valor, será executado update, se não houver
                // será executado um create.
                FinanceiroRepository.save(financeiro);
            }
        } catch (Exception ex) {
            return "erro";
        }

        return "redirect:/financas";
    }

}
