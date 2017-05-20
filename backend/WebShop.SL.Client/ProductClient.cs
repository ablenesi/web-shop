using System.ServiceModel;

using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;

namespace WebShop.SL.Client
{
    public class ProductClient : ClientBase<IProductService>, IProductService
    {
        public void Create(ProductDTO product)
        {
            Channel.Create(product);
        }
    }
}
