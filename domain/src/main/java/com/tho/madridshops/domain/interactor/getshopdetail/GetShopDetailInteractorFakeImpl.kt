package com.tho.madridshops.domain.interactor.getshopdetail

import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shop

class GetShopDetailInteractorFakeImpl : GetShopDetailInteractor {
    override fun execute(success: SuccessCompletion<Shop>, error: ErrorCompletion) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shop = createFakeShopDetail()
            success.successCompletion(shop)
        } else {
            error.errorCompletion("Error while accesing the Repository")
        }
    }

    fun createFakeShopDetail(): Shop {
        val shop = Shop(
                33,
                "HERMÉS",
                "https://madrid-shops.com/media/shops/hermes-small.jpg",
                "https://madrid-shops.com/media/shops/logo-hermes-200.jpg",
                "Calle Jose Ortega y Gasset 12",
                "https://spain.hermes.com/",
                "40.4302291".toFloat(),
                "-3.6854465999999775".toFloat(),
                "The Hermès brand goes one step beyond luxury.  Since 1837, the French giant has signed the most exquisite and delicate accessories, made by artisans who need up to four years of training to capture the essence that for five generations  they have passed on to their items: scarves, ties, leather goods, perfumes, jewelery and even their line for the Home  have no rival.  All this can be contemplated and purchased in its renovated  shop in Ortega y Gasset. \r\n The Hermès dynasty has converted the world of accessories into cult objects, which even have a waiting list of up to two years to acquire.  With the Grace bag, which Grace Kelly  immortalized on the cover of Life magazine, or the  Birkin model, named after actress and singer Jane Birkin. \r\n But the lines of this French brand also include  the season’s male and female ready to wear;   behind these right from the beginning are the designers Véronique Nichanian  and Jean-Louis, the Hermès woman today dresses\r\nwith clothes by the vanguard Jean  Paul Gaultier.",
                "La firma Hermès va un paso más allá del lujo. Desde 1837, el imperio francés ha rubricado los más exquisitos y delicados complementos, elaborados por artesanos que necesitan de hasta cuatro años de formación para captar la esencia que durante cinco generaciones han transmitido a sus artículos: pañuelos, corbatas, productos de marroquinería, perfumes, joyas e incluso su línea para el hogar no tienen competidor posible. Todo ello se puede admirar y adquirir en su renovada tienda de la calle Ortega y Gasset.\r\nLa dinastía Hermès ha convertido el mundo de los accesorios en auténticos objetos de culto, que llegan a tener detrás incluso una lista de espera de hasta dos años para poder adquirirlos. Así ocurre con el bolso Grace, aquel que inmortalizó Grace Kelly en la portada de la revista Life, o el modelo Birkin, en honor de la cantante y actriz Jane Birkin. \r\nPero las líneas de la marca francesa también incluyen propuestas prêt à porter masculinas y femeninas; mientras que detrás de las primeras se encuentra desde el principio los diseños de Véronique Nichanian y Jean-Louis, a la mujer Hermès la viste en la actualidad el rompedor Jean Paul Gaultier.",
                "Monday to Saturday: 10: 30-20: 30",
                "lun-sab 10:30-20:30"
        )
        return shop
    }

}