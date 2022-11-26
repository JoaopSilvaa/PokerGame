import java.util.Scanner;

public class Poker {
    public static void main(String[] args) {
        class Carta {
            public String nome;
            public String naipe;

            public Carta(String nome, String naipe) {
                this.nome = nome;
                this.naipe = naipe;
            }

            public String getNome() {
                return this.nome;
            }

            public String setNome(String nome) {
                this.nome = nome;
                return this.nome;
            }

            public String getNaipe() {
                return this.nome;
            }

            public String setNaipe(String nome) {
                this.nome = nome;
                return this.nome;
            }
        }

        class Baralho {
            public Carta[] cartas;

            public Baralho() {
                Carta[] cartasCopas = new Carta[13];
                Carta[] cartasEspadas = new Carta[13];
                Carta[] cartasOuros = new Carta[13];
                Carta[] cartasPaus = new Carta[13];
                Carta[] baralho = new Carta[52];

                for (int i = 0; i < cartasCopas.length; i ++) {
                    if (i == 0) {
                        cartasCopas[i] = new Carta("Ás", "Copas");
                    } else if (i == 10) {
                        cartasCopas[i] = new Carta("Valete", "Copas");
                    } else if (i == 11) {
                        cartasCopas[i] = new Carta("Dama", "Copas");
                    } else if (i == 12) {
                        cartasCopas[i] = new Carta("Rei", "Copas");
                    } else {
                        cartasCopas[i] = new Carta("" + (i+1), "Copas");
                    }
                }
                for (int i = 0; i < cartasEspadas.length; i ++) {
                    if (i == 0) {
                        cartasEspadas[i] = new Carta("Ás", "Espadas");
                    } else if (i == 10) {
                        cartasEspadas[i] = new Carta("Valete", "Espadas");
                    } else if (i == 11) {
                        cartasEspadas[i] = new Carta("Dama", "Espadas");
                    } else if (i == 12) {
                        cartasEspadas[i] = new Carta("Rei", "Espadas");
                    } else {
                        cartasEspadas[i] = new Carta("" + (i+1), "Espadas");
                    }
                }
                for (int i = 0; i < cartasOuros.length; i ++) {
                    if (i == 0) {
                        cartasOuros[i] = new Carta("Ás", "Ouros");
                    } else if (i == 10) {
                        cartasOuros[i] = new Carta("Valete", "Ouros");
                    } else if (i == 11) {
                        cartasOuros[i] = new Carta("Dama", "Ouros");
                    } else if (i == 12) {
                        cartasOuros[i] = new Carta("Rei", "Ouros");
                    } else {
                        cartasOuros[i] = new Carta("" + (i+1), "Ouros");
                    }
                }
                for (int i = 0; i < cartasPaus.length; i ++) {
                    if (i == 0) {
                        cartasPaus[i] = new Carta("Ás", "Paus");
                    } else if (i == 10) {
                        cartasPaus[i] = new Carta("Valete", "Paus");
                    } else if (i == 11) {
                        cartasPaus[i] = new Carta("Dama", "Paus");
                    } else if (i == 12) {
                        cartasPaus[i] = new Carta("Rei", "Paus");
                    } else {
                        cartasPaus[i] = new Carta("" + (i+1), "Paus");
                    }
                }
                for (int i=0; i< cartasCopas.length; i ++) {
                    baralho[i] = cartasCopas[i];
                }
                for (int i=13; i <= 25; i ++) {
                    baralho[i] = cartasEspadas[i-13];
                }
                for (int i=26; i <= 38; i ++) {
                    baralho[i] = cartasOuros[i-26];
                }
                for(int i=39; i< baralho.length; i ++){
                    baralho[i] = cartasPaus[i-39];
                }

                this.cartas = baralho;
            }

            public void embaralhar() {
                for (int i = 0; i < 50; i ++) {
                    float div1 = Math.round(Math.random() * (this.cartas.length - 1));
                    int index1 = Math.round(div1);
                    float div2 = Math.round(Math.random() * (this.cartas.length - 1));
                    int index2 = Math.round(div2);
                    if (index1 != index2) {
                        Carta carta1 = this.cartas[index1];
                        Carta carta2 = this.cartas[index2];
                        this.cartas[index1] = carta2;
                        this.cartas[index2] = carta1;
                    }
                }
            }

            public Carta[] darCartas(){
                try {
                    Carta[] newBaralho = new Carta[this.cartas.length - 1];
                    for(int i = 0; i < newBaralho.length; i ++) {
                        newBaralho[i] = this.cartas[i+1];
                    }
                    Carta[] newNewBaralho = new Carta[newBaralho.length - 1];
                    for(int i = 0; i < newNewBaralho.length; i ++) {
                        newNewBaralho[i] = newBaralho[i+1];
                    }
                    Carta[] cartas = new Carta[2];
                    cartas[0] = this.cartas[0];
                    cartas[1] = this.cartas[1];
                    this.cartas = newNewBaralho;

                    return cartas;
                } catch(RuntimeException e) {
                    System.out.println("Não há mais cartas no baralho");
                    Carta[] cartas = new Carta[2];
                    return cartas;
                }
            }

            public boolean temCarta() {
                if (this.cartas.length != 0) {
                    return true;
                } else {
                    return false;
                }
            }

            public void imprimeBaralho() {
                for (int i=0; i < this.cartas.length; i++) {
                    System.out.println(this.cartas[i].nome + " " + this.cartas[i].naipe);
                }
            }
        }

        Baralho poker = new Baralho();
        poker.embaralhar();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos jogadores tem na mesa? ");
        int qtdJogadores = scanner.nextInt();
        try {
            if (qtdJogadores < 1) {
                throw new RuntimeException("Quantidade de jogadores muito baixa");
            }
            Carta[][] jogadores = new Carta[qtdJogadores][2];
            int[] apostas = new int[qtdJogadores];
            for(int i = 0; i < qtdJogadores; i ++) {
                jogadores[i] = poker.darCartas();
            }


            for (int i = 0; i <qtdJogadores; i ++) {
                System.out.println("Jogador " + (i+1) + ", abaixo estão suas cartas. Quer apostar? (s/n)");
                System.out.println(jogadores[i][0].nome + " " + jogadores[i][0].naipe);
                System.out.println(jogadores[i][1].nome + " " + jogadores[i][1].naipe);
                String choice = scanner.next();
                if (choice.equals("s") || choice.equals("S")) {
                    System.out.println("Jogador " + (i+1) + " qual o valor da aposta?");
                    apostas[i] = scanner.nextInt();
                } else if (choice.equals("n") || choice.equals("N")) {
                    apostas[i] = 0;
                } else {
                    throw new RuntimeException("Seleção inválida");
                }
            }

            System.out.println("--------------------------");
            System.out.println("Mesa: ");
            for (int i = 0; i < 5; i ++) {
                System.out.println(poker.cartas[i].nome + " " + poker.cartas[i].naipe);
            }

            int sumApostas = 0;
            for (int i = 0; i < qtdJogadores; i ++) {
                sumApostas += apostas[i];
            }

            System.out.println("--------------------------");
            System.out.println("Quem ganhou? (digite o número do jogador)");
            int winner = scanner.nextInt();

            System.out.println("--------------------------");
            System.out.println("O jogador " + winner + " é o vencedor");
            System.out.println("Ganhou a quantia de R$ " + sumApostas + ",00 ");

        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}
