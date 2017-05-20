using System.ServiceModel;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.SL.ServiceLayerContracts
{
    [ServiceContract]
    public interface IProductService
    {
        [OperationContract]
        void Create(ProductDTO product);
    }
}
